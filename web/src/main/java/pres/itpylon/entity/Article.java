package pres.itpylon.entity;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Author sunyulong
 * Date Created on 2017/9/8 15:11
 */
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "标题不能为空")
    @Size(min = 2, max = 50)
    @Column(nullable = false, length = 50)
    private String title;

    @Lob  // 大对象，映射 MySQL 的 Long Text 类型
    @Basic(fetch = FetchType.LAZY) // 懒加载
    @NotEmpty(message = "内容不能为空")
    @Size(min = 2)
    @Column(nullable = false)
    private String body;

    private String author;

    @org.hibernate.annotations.CreationTimestamp
    private Timestamp createTime;
    private int starNum = 0;
    private int favNum = 0;
    private int kind = 0;
    private String kindName = "默认分类";
    private String lables = "";

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getAuthor() {
        return author;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public int getStarNum() {
        return starNum;
    }

    public int getFavNum() {
        return favNum;
    }

    public int getKind() {
        return kind;
    }

    public String getKindName() {
        return kindName;
    }

    public String getLables() {
        return lables;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setStarNum(int starNum) {
        this.starNum = starNum;
    }

    public void setFavNum(int favNum) {
        this.favNum = favNum;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public void setLables(String lables) {
        this.lables = lables;
    }

    public Article() {

    }

    public Article(String title, String body, String author, Timestamp createTime, int starNum, int favNum, int kind, String kindName, String lables) {
        this.title = title;
        this.body = body;
        this.author = author;
        this.createTime = createTime;
        this.starNum = starNum;
        this.favNum = favNum;
        this.kind = kind;
        this.kindName = kindName;
        this.lables = lables;
    }
}

