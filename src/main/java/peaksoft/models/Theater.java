package peaksoft.models;

public class Theater {
    private Long id;
    private String name;
    private String location;

    public Theater() {
    }

    public Theater(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public Theater(Long id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Theater(String theaterName) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "\nTheater{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
