package com.example.movies.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface usersRepository extends JpaRepository<users,Integer> {
    users findByEmail(String email);

}
