import org.sql2o.*;
import java.util.List;

public class Recipient extends Patient {

    public Recipient(String name, String email, String condition, String age, String bgroup) {

        this.email = email;
        this.age = age;
        this.condition = condition;
        this.name = name;
        this.bgroup = bgroup;

    }

    @Override
    public boolean equals(Object otherRecipient) {
        if (!(otherRecipient instanceof Recipient)) {
            return false;
        } else {
            Recipient newRecipient = (Recipient) otherRecipient;
            return this.getName().equals(newRecipient.getName());
        }
    }

    public void save() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "INSERT INTO recipients (name, email, age, condition, bgroup) VALUES (:name, :email, :age, :condition, :bgroup);";
            this.id = (int) con.createQuery(sql, true).addParameter("name", this.name).addParameter("email", this.email)
                    .addParameter("age", this.age).addParameter("condition", this.condition).addParameter("bgroup", this.bgroup).executeUpdate().getKey();
        }
    }

    public static List<Recipient> all() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM recipients;";
            return con.createQuery(sql).executeAndFetch(Recipient.class);
        }
    }

    public static Recipient find(int id) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM recipients WHERE id=:id;";
            Recipient recipient = con.createQuery(sql).addParameter("id", id).executeAndFetchFirst(Recipient.class);
            return recipient;
        }
    }

    public void updateName(String name, String email, String condition, String age) {
        try (Connection con = DB.sql2o.open()) {
            String sql = "UPDATE recipients SET name=:name, email=:email, condition=:condition, age=:age, bgroup=:bgroup WHERE id=:id;";
            con.createQuery(sql).addParameter("id", id).addParameter("name", name).addParameter("email", email)
                    .addParameter("age", age).addParameter("condition", condition).addParameter("bgroup", this.bgroup).executeUpdate();
        }
    }

    public void delete() {
        try (Connection con = DB.sql2o.open()) {
            String sql = "DELETE FROM recipients WHERE id=:id;";
            con.createQuery(sql).addParameter("id", id).executeUpdate();
        }
    }

}