package com.example.reddit.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.reddit.model.Subreddit;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit,Long> {

	Subreddit findByName(String name);
}
