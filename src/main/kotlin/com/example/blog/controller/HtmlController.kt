package com.example.blog.controller

import com.example.blog.BlogProperties
import com.example.blog.entity.Article
import com.example.blog.entity.User
import com.example.blog.extensions.format
import com.example.blog.respository.ArticleRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.lang.IllegalArgumentException
import javax.jws.WebParam

@Controller
class HtmlController(private val articleRepository: ArticleRepository,
                     private val properties: BlogProperties){

    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = properties.title
        model["banner"] = properties.banner
        model["articles"] = articleRepository.findAllByOrderByAddedAtDesc().map { it.render() }
        return "blog";
    }

    @GetMapping("/article/{slug}")
    fun article(@PathVariable slug: String, model: Model): String {
        val article = articleRepository.findBySlug(slug)?.render()?: throw IllegalArgumentException("Wrong article slug provided")
        model["title"]= article.title
        model["article"]= article
        return  "article"
    }

    fun Article.render() = RenderedArticle(
            slug,
            title,
            headLine,
            content,
            author,
            addedAt.format()
    )

    data class RenderedArticle(
            val slug: String,
            val title: String,
            val headline: String,
            val content: String,
            val author: User,
            val addedAt: String
    )
}