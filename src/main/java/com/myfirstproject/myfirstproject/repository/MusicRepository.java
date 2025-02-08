package com.myfirstproject.myfirstproject.repository;

import com.myfirstproject.myfirstproject.model.Music;
import com.myfirstproject.myfirstproject.model.Music.AudioQuality;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;


@Repository
public interface MusicRepository extends MongoRepository<Music, String> { //Este extends proporciona quatro metodos basicos do MongoRepository para o tipo <Tipo da entidade, Tipo do campo ID da entidade>

/**
    Nivel Basico
  
    musicRepository.findAll(); → Busca todas as músicas
    musicRepository.findById(id); → Busca por ID
    musicRepository.save(music); → Salva um novo registro
    musicRepository.deleteById(id); → Remove uma música pelo ID
**/
   
    List<Music> findByReleaseYear(Integer releaseYear);
    List<Music> findByArtist(String artist);
    List<Music> findByAlbum(String album);

    List<Music> findByReleaseDate(LocalDate releaseDate);//Metodo Experimental

/* 

    Nivel Intermediario

    Estes são metodos de consulta derivados e criados automaticamente pelo nome do metodo que são possiveis pelo "findBy", e que no caso do "Id" já está sendo proporcionado automaticamente pelo Extends  

    musicRepository.findByArtist("Queen"); → Retorna todas as músicas do artista Queen
    musicRepository.findByAlbum("A Night at the Opera"); → Retorna músicas de um álbum específico
    musicRepository.findByReleaseYear(1975); → Retorna músicas lançadas em 1975
  
*/

    List<Music> findByGenreIn(List<String> genres);
    List<Music> findByRatingGreaterThanEqual(Double rating);
    List<Music> findByReleaseYearGreaterThan(Integer year);
    List<Music> findByIsExplicitTrue();
    List<Music> findByLyricsIsNull();

    List<Music> findByAlbumIn(List<String> albuns);//Metodo Experimento
    List<Music> findByArtistIn(List<String> artists);//Metodo Experimento
    List<Music> findByDurationGreaterThanEqual(Double rating);//Metodo Experimental
/*
    Nivel Intermediario e avançado

    Estes são metodos de consulta derivados e criados automaticamente pelo nome do metodo, mas também utilizam parametros adicionados do mongoDB como ($in; $gt; $gte; isExplicit:true; $exists: false) para então serem distintas das anteriores

    musicRepository.findByGenreIn(List.of("Rock", "Jazz")); → Retorna músicas que pertencem a um dos gêneros especificados
    musicRepository.findByRatingGreaterThanEqual(4.5); → Retorna músicas com nota maior ou igual a 4.5
    musicRepository.findByReleaseYearGreaterThan(2000); → Retorna músicas lançadas após 2000
    musicRepository.findByIsExplicitTrue(); → Retorna músicas com letras explícitas
    musicRepository.findByLyricsIsNull(); → Retorna músicas sem letras cadastradas  
 */

    
    List<Music> findByArtistAndFeaturedArtistsContaining(String artist, String featuredArtist); 


    //List<Music> findByArtistAndRealeseYearContaining(String artist, Integer releaseYear);//Metodo Experimento
    /*  
    Nivel avançado
    
    Este seria um metodo de consulta derivado que utiliza dois parametros para a realização com conectivo para sua realização 
    
*/

/* 
    @Query("{ 'artist': ?0, 'featuredArtists': { $in: [?1] } }")
    List<Music> findByArtistAndFeaturedArtists(String artist, String featuredArtist);

    @Query("{ 'releaseYear': { $gte: ?0, $lte: ?1 } }")
    List<Music> findByReleaseYearBetween(Integer startYear, Integer endYear);

    @Query("{ 'genre': { $regex: ?0, $options: 'i' } }")
    List<Music> findByGenreLikeIgnoreCase(String genre);
*/
/* 
    // Paginação de músicas por artista
    Page<Music> findByArtist(String artist, Pageable pageable);

    // Projeção para retornar apenas nome da música e artista
    @Query(value = "{ 'artist': ?0 }", fields = "{ 'title': 1, 'artist': 1 }")
    List<Music> findTitleAndArtistByArtist(String artist);

    // Agregação: Retorna a média das avaliações das músicas de um artista
    @Aggregation(pipeline = {
        "{ $match: { 'artist': ?0 } }",
        "{ $group: { _id: null, averageRating: { $avg: '$rating' } } }"
    })
    Double findAverageRatingByArtist(@Param("artist") String artist);

*/
    List<Music> findByPriceLessThanEqual(BigDecimal price);
    List<Music> findByPriceGreaterThan(BigDecimal price);
    List<Music> findByAudioQuality(AudioQuality audioQuality);
    List<Music> findByCreatedAtAfter(Instant createdAt);
    List<Music> findByAlbumCoverImageIsNotNull();
    List<Music> findByAlbumCoverImageIsNull();
    Page<Music> findByAlbumCoverImageIsNotNull(Pageable pageable);
    Page<Music> findByAlbumCoverImageIsNull(Pageable pageable);
    Page<Music> findByReleaseYear(Integer releaseYear, Pageable pageable);
    Page<Music> findByRatingGreaterThanEqual(Double rating, Pageable pageable);
    Page<Music> findByDurationGreaterThanEqual(Double duration, Pageable pageable);
    
}
