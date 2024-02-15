package com.example.TheFit.security;

import org.springframework.data.repository.CrudRepository;

public interface RedisRepository extends CrudRepository<RefreshToken,String>{
}
