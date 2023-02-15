package hiber.model;


import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "car")
public class Car implements Serializable {
    @Id
    private Long id;
    @Column(name = "model")
    private String model;
    @Column(name = "series")
    private int series;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Car() {
    }

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "Авто " + getModel() + " series " + getSeries();
    }
}
