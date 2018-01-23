package ua.dp.skillsup.hibernate;

import javax.persistence.*;

@Entity
@Table(name = "test")
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String content;

    public TestEntity() {
    }

    public TestEntity(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TestEntity{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
