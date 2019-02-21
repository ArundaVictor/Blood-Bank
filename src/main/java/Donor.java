import org.sql2o.*;
import java.util.List;

public class Donor extends Patient {

    public Donor(String name, String email, String condition, String age, int id) {

        this.email = email;
        this.age = age;
        this.condition = condition;
        this.id = id;
        this.name = name;

    }

    @Override
    public boolean equals(Object otherDonor) {
        if (!(otherDonor instanceof Recipient)) {
            return false;
        } else {
            Recipient newDonor = (Recipient) otherDonor;
            return this.getName().equals(newDonor.getName());
        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO animals (name, email, age, condition) VALUES (:name, :email, :age, :condition);";
            this.id = (int) con.createQuery(sql, true).addParameter("name", this.name).addParameter("email", this.email)
                    .addParameter("age", this.age).addParameter("condition", this.condition).executeUpdate().getKey();
        }
    }

    public static List<Recipient> all() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM donors;";
            return con.createQuery(sql).executeAndFetch(Recipient.class);
        }
    }

    public static Recipient find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM donors WHERE id=:id;";
            Recipient donor = con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Recipient.class);
            return donor;
        }
    }

    public void updateName(String name, String email, String condition, String age) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "UPDATE donors SET name=:name, email=:email, condition=:condition, age=:age WHERE id=:id;";
            con.createQuery(sql).addParameter("id", id).addParameter("name", name).addParameter("email", email)
                    .addParameter("age", age).addParameter("condition", condition).executeUpdate();
        }
    }

    public void delete() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM donors WHERE id=:id;";
            con.createQuery(sql).addParameter("id", id).executeUpdate();
        }
    }

}