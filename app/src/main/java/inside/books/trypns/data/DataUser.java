package inside.books.trypns.data;

public class DataUser {

    public static final String TABLE_NAME = "datauser";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAMA + " TEXT,"
                    + COLUMN_EMAIL + " TEXT,"
                    + COLUMN_PASSWORD + " TEXT"
                    + ")";

    String nama;

    public DataUser(String nama, String email, String password) {
        this.nama = nama;
        this.email = email;
        this.password = password;
    }

    String email;
    String password;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
