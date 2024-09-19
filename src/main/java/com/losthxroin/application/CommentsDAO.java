package com.losthxroin.application;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CommentsDAO {
    private List<Comment> db;

    public CommentsDAO() {
        this.db = new ArrayList<>();
        db.add( new Comment("Ярик",1,"ало"));
    }

    public void create(Comment comment){
        db.add(comment);
    }
    public List<Comment> read(int applicationFormsID){
        List<Comment> comments = new ArrayList<>();
        for (int i = 0; i < db.size(); i++) {
            if (db.get(i).getApplicationFormsID() == applicationFormsID){
                comments.add(db.get(i));
            }
        }
        return comments;
    }
}
