import java.util.ArrayList;
import java.util.List;

class StudentV1 {
    private String name;
    private int score;

    public StudentV1(String name, int score) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("nama tidak boleh kosong");
        }
        validateScore(score);
        this.name = name;
        this.score = score;
    }

    private void validateScore(int score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("score harus 0..100");
        }
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("nama tidak boleh kosong");
        }
        this.name = name;
    }

    public void setScore(int score) {
        validateScore(score);
        this.score = score;
    }

    public String toString() {
        return "StudentV1{name='" + name + "', score=" + score + "}";
    }
}

class Student {
    private String name;
    private int score;

    public Student(String name, int score) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("nama tidak boleh kosong");
        }
        validateScore(score);
        this.name = name;
        this.score = score;
    }

    private void validateScore(int score) {
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("score harus 0..100");
        }
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("nama tidak boleh kosong");
        }
        this.name = name;
    }

    public void addScore(int delta) {
        int next = this.score + delta;
        validateScore(next);
        this.score = next;
    }

    public void reduceScore(int delta) {
        int next = this.score - delta;
        validateScore(next);
        this.score = next;
    }

    public String toString() {
        return "Student{name='" + name + "', score=" + score + "}";
    }
}

class Team {
    private final List<Student> members = new ArrayList<>();

    public void addMember(Student s) {
        if (s == null) {
            throw new IllegalArgumentException("member tidak boleh null");
        }
        members.add(s);
    }

    public List<Student> getMembers() {
        return new ArrayList<>(members);
    }

    public String toString() {
        return "Team{members=" + members + "}";
    }
}

final class ImmutableStudent {
    private final String name;
    private final int score;

    public ImmutableStudent(String name, int score) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("nama tidak boleh kosong");
        }
        if (score < 0 || score > 100) {
            throw new IllegalArgumentException("score harus 0..100");
        }
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public ImmutableStudent withAddedScore(int delta) {
        int next = this.score + delta;
        if (next < 0 || next > 100) {
            throw new IllegalArgumentException("hasil penjumlahan score harus 0..100");
        }
        return new ImmutableStudent(this.name, next);
    }

    public String toString() {
        return "ImmutableStudent{name='" + name + "', score=" + score + "}";
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Uji StudentV1 ===");
        StudentV1 a = new StudentV1("Alice", 80);
        System.out.println(a);
        a.setScore(95);
        System.out.println(a);
        try {
            a.setScore(120);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Uji Student ===");
        Student b = new Student("Bob", 90);
        System.out.println(b);
        b.addScore(5);
        System.out.println(b);
        b.reduceScore(10);
        System.out.println(b);
        try {
            b.addScore(20);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== Uji Team ===");
        Team t = new Team();
        t.addMember(new Student("Carol", 70));
        t.addMember(new Student("Dan", 60));
        System.out.println(t);

        List<Student> view = t.getMembers();
        view.clear();
        System.out.println("Setelah clear() list luar: " + t);

        System.out.println("\n=== Uji ImmutableStudent ===");
        ImmutableStudent is = new ImmutableStudent("Eve", 50);
        System.out.println(is);
        ImmutableStudent is2 = is.withAddedScore(25);
        System.out.println(is2);
        try {
            is.withAddedScore(-60);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
