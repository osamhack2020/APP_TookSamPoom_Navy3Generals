import java.text.SimpleDateFormat;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHlper extends SQLiteOpenHelper{
    public DBHelper(Context context){
        super(context, "Test.db",null,1);
    }

    public void onCreate(SQLiteDatabase db){
        String sql1 = "CREATE TABLE User("
                    +"id INTEGER primary key not null, "
                    +"name TEXT not null,"
                    +"age INTEGER not null,"
                    +"sex TEXT not null)";

        String sql2 = "CREATE TABLE Record("
                    +"id INTEGER not null, "
                    +"push_up INTEGER,"
                    +"sit_up INTEGER,"
                    +"running INTEGER,"
                    +"date DATE not null)";
        db.execSQL(sql1);
    }

    //DB에 연결
    public static SQLiteDatabase connectDb(Context context){
        DBhelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        return db;
    }

    public static void getUser(SQLiteDatabse db, int id,
                                        TextView text1,
                                        TextView text2,
                                        TextView text3,
                                        TextView text4,
                                        TextView text5){
        String sql = "SELECT name,"
                    +"height,"
                    +"weight,"
                    +"age,"
                    +"sex FROM User"
                    +"WHERE id="+id+"";

        Cursor c = db.rawQuery(sql,null);
        while(c.moveToNext()){
            int name_pos = c.getColumnIndex("name");
            int height_pos = c.getColumnIndex("height");
            int weight_pos = c.getColumnIndex("weight");
            int age_pos = c.getColumnIndex("age");
            int sex_pos = c.getColumnIndex("sex");

            String name = c.getString(name_pos);
            int height = c.getInt(height);
            int weight = c.getInt(weight);
            int age = c.getInt(age);
            String sex = c.getString(sex);

            text1.setText(name);
            text2.setText(Int.toString(height));
            text3.setText(Int.toString(weight));
            text4.setText(Int.toString(age));
            text5.setText(sex);
        }
    }
    
    public static int setUser(SQLiteDatabase db,JSONObject object){
        String sql = "INSERT INTO User(name,"
                    +"height,"
                    +"weight,"
                    +"age,"
                    +"sex) VALUES(?,?,?,?,?)";
        String name = object.getString("name");
        int height = object.getInt("height");
        int weight = object.getInt("weight");
        int age = object.getInt("age");
        String sex = object.getString("sex");
        
        String[] value = {name, 
                        Int.toString(height), 
                        Int.toString(weight), 
                        Int.toString(age), 
                        sex};
        db.execSQL(sql,value);
        return (db.lastId);
    }  
    public static void updateUser(SQLiteDatabase db,JSONObject object, int id){
        String sql = "UPDATE User SET name=?,"
                    +"height=?,"
                    +"weight=?,"
                    +"age=?,"
                    +"sex=? WHERE id="+id+"";
        String name = object.getString("name");
        int height = object.getInt("height");
        int weight = object.getInt("weight");
        int age = object.getInt("age");
        String sex = object.getString("sex");
        
        String[] value = {name, 
                        Int.toString(height), 
                        Int.toString(weight), 
                        Int.toString(age), 
                        sex};
        db.execSQL(sql,value);
    }  

    public static void setPushUpRecord(SQLiteDatabase db,int id, int repeats){
        String sql = "INSERT INTO Record(id,"
                    +"push_up,"
                    +"date) VALUES(?,?,?)";
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        String date = sdf.format(new Date());

        String[] value = {Int.toString(id), 
                        Int.toString(repeats),
                        date};
        db.execSQL(sql,value);
    }  

    public static void setSitUpRecord(SQLiteDatabase db,int id, int repeats){
        String sql = "INSERT INTO Record(id,"
                    +"sit_up,"
                    +"date) VALUES(?,?,?)";
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        String date = sdf.format(new Date());

        String[] value = {Int.toString(id), 
                        Int.toString(repeats),
                        date};
        db.execSQL(sql,value);
    }  

    public static void setRunniongRecord(SQLiteDatabase db,int id, int time){
        String sql = "INSERT INTO Record(id,"
                    +"running,"
                    +"date) VALUES(?,?,?)";
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        String date = sdf.format(new Date());

        String[] value = {Int.toString(id), 
                        Int.toString(time),
                        date};
        db.execSQL(sql,value);
    }  
}