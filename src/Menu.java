import java.sql.*;
import java.util.Scanner;

public class Menu {
    Scanner in = new Scanner(System.in);
    public static final String database_url = "jdbc:mysql://localhost:3306/MFD";
    public static java.sql.Connection con;
    private String name;
    private int age;
    private String birthCountry;
    private String gender;
    private int artistId;
    private int labelId;

    public void baseMenu() {
        System.out.println("Welcome, please select an option:\n1. View artists\n2. View Albums\n3. View record labels" +
                "\n4. Add artist\n5.Quit");
    }

    public void case1() {
        try {
            con = DriverManager.getConnection(database_url, "root", "Kattle1781!");
            Statement s = con.createStatement();

            String sql = "SELECT artist_name, artist_gender, artist_age, birth_country FROM Artists ";

            ResultSet rs = s.executeQuery(sql);

            if (rs != null) {
                while (rs.next()) {
                    System.out.println("\nArtist name: " + rs.getString("artist_name"));
                    System.out.println("Artist gender: " + rs.getString("artist_gender"));
                    System.out.println("Artist age: " + rs.getString("artist_age"));
                    System.out.println("Artist birth country: " + rs.getString("birth_country") + "\n");
                }

            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.exit(1);
        }
    }

    public void case2() {
        try {
            con = DriverManager.getConnection(database_url, "root", "Kattle1781!");
            Statement s = con.createStatement();

            String sql = "SELECT artist_name, album_name, genre, track1_name, track2_name, track3_name FROM Album" + "\n" +
                    "INNER JOIN Artists" +
                    "       ON Artists.artist_id = Album.associated_artist_id" + "\n" +
                    "ORDER BY album_name";

            ResultSet rs = s.executeQuery(sql);

            if (rs != null) {
                while (rs.next()) {
                    System.out.println("\nArtist name: " + rs.getString("artist_name"));
                    System.out.println("Album name: " + rs.getString("album_name"));
                    System.out.println("Genre: " + rs.getString("genre"));
                    System.out.println("Track 1 name: " + rs.getString("track1_name"));
                    System.out.println("Track 2 name: " + rs.getString("track2_name"));
                    System.out.println("Track 3 name: " + rs.getString("track3_name") + "\n");
                }

            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.exit(1);
        }
    }

    public void case3() {
        try {
            con = DriverManager.getConnection(database_url, "root", "Kattle1781!");
            Statement s = con.createStatement();

            String sql = "SELECT label_name, adress FROM Record_label" + "\n" +
                    "ORDER BY label_name";

            ResultSet rs = s.executeQuery(sql);

            if (rs != null) {
                while (rs.next()) {
                    System.out.println("\nLabel name: " + rs.getString("label_name"));
                    System.out.println("Label adress: " + rs.getString("adress") + "\n");
                }

            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.exit(1);
        }
    }

    public void case4() {
        try {
            con = DriverManager.getConnection(database_url, "root", "Kattle1781!");
            String sql = "INSERT INTO Artists(artist_name, artist_id, artist_gender, artist_age," +
                    "birth_country, assoiated_label_id)" + "\n" +
                    "VALUES(?, ?, ?, ?, ?, ?)";
            try (
                    PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, name);
                ps.setInt(2, artistId);
                ps.setString(3, gender);
                ps.setInt(4, age);
                ps.setString(5, birthCountry);
                ps.setInt(6, labelId);
                ps.executeUpdate();
                System.out.println("Artist has been added!");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.exit(1);
        }
    }
    public void artistData() {
        System.out.println("Please enter the artist's name:");
        name = readString();
        System.out.println("Please enter the artist's gender:");
        gender = readString();
        System.out.println("Please enter the artist's id:");
        artistId = readInt();
        System.out.println("Please enter the artist's country of birth");
        birthCountry = readString();
        System.out.println("Please enter the artist's age:");
        age = readInt();
        System.out.println("Please enter the associated label's id:");
        labelId = readInt();

    }
    public int readInt() {
        Scanner scanner = new Scanner(System.in);
        boolean validChoice = false;
        int choice = -1;
        while (!validChoice) {
            System.out.print(" ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                validChoice = true;
            } else {
                System.err.println("Please input a number");
                scanner.nextLine();
            }
        }
        return choice;
    }
    public String readString() {
        Scanner scanner = new Scanner(System.in);
        boolean validChoice = false;
        String choice2 = " ";
        while (!validChoice) {
            System.out.print(" ");
            if (scanner.hasNextLine()) {
                choice2 = scanner.nextLine();
                validChoice = true;
            } else {
                System.err.println("Please input a string");
                scanner.nextLine();
            }
        }
        return choice2;
    }
}

