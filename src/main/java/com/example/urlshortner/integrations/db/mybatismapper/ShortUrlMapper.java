package com.example.urlshortner.integrations.db.mybatismapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ShortUrlMapper {

    @Insert("insert into shorturl_map(id, short_url, long_url) values (#{id}, #{shortUrl}, #{longUrl})")
    public int addShortUrl(long id, String shortUrl, String longUrl);

    @Select("select long_url from shorturl_map where short_url=#{shortUrl}")
    public String findLongUrl(String shortUrl);

}
