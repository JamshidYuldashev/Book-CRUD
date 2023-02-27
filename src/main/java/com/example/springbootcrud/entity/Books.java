package com.example.springbootcrud.entity;

import com.example.springbootcrud.dto.BookCreateDto;
import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookid;
    @Column
    private String bookname;
    @Column
    private String author;
    @Column
    private int price;

    public Books(BookCreateDto dto) {
        this.bookname=dto.getBookname();
        this.author=dto.getAuthor();
        this.price=dto.getPrice();
    }

    public Books() {
    }
    public Long getBookid()
    {
        return bookid;
    }
    public void setBookid(Long bookid)
    {
        this.bookid = bookid;
    }
    public String getBookname()
    {
        return bookname;
    }
    public void setBookname(String bookname)
    {
        this.bookname = bookname;
    }
    public String getAuthor()
    {
        return author;
    }
    public void setAuthor(String author)
    {
        this.author = author;
    }
    public int getPrice()
    {
        return price;
    }
    public void setPrice(int price)
    {
        this.price = price;
    }
    }