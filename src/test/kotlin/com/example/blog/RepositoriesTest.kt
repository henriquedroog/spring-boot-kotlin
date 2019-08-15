package com.example.blog

import com.example.blog.entity.Article
import com.example.blog.entity.User
import com.example.blog.respository.ArticleRepository
import com.example.blog.respository.UserRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull
import org.assertj.core.api.Assertions.*

@DataJpaTest
class RepositoriesTest @Autowired constructor(
        val entityManager: TestEntityManager,
        val userRepository: UserRepository,
        val articleRepository: ArticleRepository
) {

    @Test
    fun `When findByIdOrNull then return Article`(){
        val henrique = User("springdroog", "Henrique", "Droog")
        entityManager.persist(henrique)
        val article = Article("Spring Framework 5.0 go GA", "Dear Spring Community...", "Lorem Ipsum", henrique)
        entityManager.persist(article)
        entityManager.flush()
        val found = articleRepository.findByIdOrNull(article.id!!)
        assertThat(found).isEqualTo(article)
    }

    @Test
    fun `When findByLogin then return User`(){
        val henrique = User("springdroog", "Henrique", "Droog")
        entityManager.persist(henrique)
        entityManager.flush()
        val user = userRepository.findByLogin(henrique.login)
        assertThat(user).isEqualTo(henrique)
    }
}