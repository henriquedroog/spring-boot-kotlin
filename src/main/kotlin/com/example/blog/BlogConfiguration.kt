package com.example.blog

import com.example.blog.entity.Article
import com.example.blog.entity.User
import com.example.blog.respository.ArticleRepository
import com.example.blog.respository.UserRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {

    @Bean
    fun databaseInitializer(userRepository: UserRepository,
                            articleRepository: ArticleRepository) = ApplicationRunner {

        val smaldini = userRepository.save(User("smaldini", "Stéphane", "Maldini"))
        articleRepository.save(Article(
                title = "Reactor Bismuth is out",
                content = "dolor sit amet",
                headLine = "Lorem ipsum",
                author = smaldini
        ))
        articleRepository.save(Article(
                title = "Reactor Aluminium has landed",
                headLine = "Lorem ipsum",
                content = "dolor sit amet",
                author = smaldini
        ))
    }
}