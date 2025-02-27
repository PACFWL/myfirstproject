package com.myfirstproject.myfirstproject.service.music;

import com.myfirstproject.myfirstproject.dto.music.MusicDTO;
import com.myfirstproject.myfirstproject.mapper.MusicMapper;
import com.myfirstproject.myfirstproject.model.Music;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MusicSearchService {

    private static final Logger logger = LoggerFactory.getLogger(MusicSearchService.class);
    private final MongoTemplate mongoTemplate;

    public MusicSearchService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<MusicDTO> advancedSearch(String artist, String album, List<String> genres, Integer releaseYear,
                                        Double minRating, Integer afterYear, Boolean isExplicit,
                                        Boolean noLyrics, String featuringArtist, BigDecimal maxPrice, Boolean hasAlbumCover,
                                        Music.AudioQuality audioQuality, Instant createdAfter, Set<String> tags, 
                                        Map<String, String> metadata) {
        Query query = new Query();

        if (artist != null) query.addCriteria(Criteria.where("artist").is(artist));
        if (album != null) query.addCriteria(Criteria.where("album").is(album));
        if (genres != null && !genres.isEmpty()) query.addCriteria(Criteria.where("genre").in(genres));
        if (releaseYear != null) query.addCriteria(Criteria.where("releaseYear").is(releaseYear));
        if (minRating != null) query.addCriteria(Criteria.where("rating").gte(minRating));
        if (afterYear != null) query.addCriteria(Criteria.where("releaseYear").gt(afterYear));
        if (isExplicit != null) query.addCriteria(Criteria.where("isExplicit").is(isExplicit));
        if (noLyrics != null && noLyrics) query.addCriteria(Criteria.where("lyrics").is(null));
        if (featuringArtist != null) query.addCriteria(Criteria.where("featuredArtists").in(featuringArtist));
        if (maxPrice != null) query.addCriteria(Criteria.where("price").lte(maxPrice));
        if (hasAlbumCover != null) {
            if (hasAlbumCover) {
                query.addCriteria(Criteria.where("albumCoverImage").ne(null)); 
            } else {
                query.addCriteria(Criteria.where("albumCoverImage").is(null)); 
            }
        };
        if (audioQuality != null) query.addCriteria(Criteria.where("audioQuality").is(audioQuality));
        if (createdAfter != null) query.addCriteria(Criteria.where("createdAt").gt(createdAfter));
        if (tags != null && !tags.isEmpty()) {
            query.addCriteria(Criteria.where("tags").in(tags));
        }     
        if (metadata != null && !metadata.isEmpty()) {
            metadata.forEach((key, value) -> {
                query.addCriteria(Criteria.where("metadata." + key).is(value));
            });
        }

        logger.info("Executando busca avançada com critérios: {}", query);
        List<Music> results = mongoTemplate.find(query, Music.class);
        return results.stream().map(MusicMapper::toDTO).collect(Collectors.toList());
    }



    public Page<MusicDTO> advancedSearchPaged(String artist, String album, List<String> genres, Integer releaseYear,
                                            Double minRating, Integer afterYear, Boolean isExplicit,
                                            Boolean noLyrics, String featuringArtist, BigDecimal maxPrice, Boolean hasAlbumCover, 
                                            Music.AudioQuality audioQuality, Instant createdAfter, Set<String> tags, Map<String, String> metadata, Pageable pageable) {
        Query query = new Query();

        if (artist != null) query.addCriteria(Criteria.where("artist").is(artist));
        if (album != null) query.addCriteria(Criteria.where("album").is(album));
        if (genres != null && !genres.isEmpty()) query.addCriteria(Criteria.where("genre").in(genres));
        if (releaseYear != null) query.addCriteria(Criteria.where("releaseYear").is(releaseYear));
        if (minRating != null) query.addCriteria(Criteria.where("rating").gte(minRating));
        if (afterYear != null) query.addCriteria(Criteria.where("releaseYear").gt(afterYear));
        if (isExplicit != null) query.addCriteria(Criteria.where("isExplicit").is(isExplicit));
        if (noLyrics != null && noLyrics) query.addCriteria(Criteria.where("lyrics").is(null));
        if (featuringArtist != null) query.addCriteria(Criteria.where("featuredArtists").in(featuringArtist));
        if (maxPrice != null) query.addCriteria(Criteria.where("price").lte(maxPrice));
        if (hasAlbumCover != null) {
            if (hasAlbumCover) {
                query.addCriteria(Criteria.where("albumCoverImage").ne(null)); 
            } else {
                query.addCriteria(Criteria.where("albumCoverImage").is(null)); 
            }
        };
        if (audioQuality != null) query.addCriteria(Criteria.where("audioQuality").is(audioQuality));
        if (createdAfter != null) query.addCriteria(Criteria.where("createdAt").gt(createdAfter));
        if (tags != null && !tags.isEmpty()) {
            query.addCriteria(Criteria.where("tags").in(tags));
        }     
        if (metadata != null && !metadata.isEmpty()) {
            metadata.forEach((key, value) -> {
                query.addCriteria(Criteria.where("metadata." + key).is(value));
            });
        }

        long total = mongoTemplate.count(query, Music.class);
        
        query.with(pageable); 
        List<Music> results = mongoTemplate.find(query, Music.class);
        
        List<MusicDTO> musicDTOs = results.stream().map(MusicMapper::toDTO).collect(Collectors.toList());
        
        return new PageImpl<>(musicDTOs, pageable, total);
    }

}
