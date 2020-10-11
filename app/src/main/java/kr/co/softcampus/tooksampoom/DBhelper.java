import java.text.SimpleDateFormat;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
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
        db.close();
    }

    public static Info getUser(Context context,int id){
        DBhelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();          
        Info info = new Info();                          
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

            info.setName(c.getString(name_pos));
            info.setHeight(c.getInt(height_pos));
            info.setWeight(c.getInt(weight_pos));
            info.setAge(c.getInt(age_pos));
            info.setSex(c.getString(sex_pos));
        }
        db.close();
        return info;
    }

    public static Info getRecord(Context context,int id){
        DBhelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();          
        Info info = new Info();                          
        String sql = "SELECT push_up,"
                    +"sit_up,"
                    +"running,"
                    +"date FROM Record"
                    +"WHERE id="+id+" ORDER BY date DESC LIMIT 10";
        Cursor c = db.rawQuery(sql,null);
        while(c.moveToNext()){
            int push_up_pos = c.getColumnIndex("push_up");
            int sit_up_pos = c.getColumnIndex("sit_up");
            int running_pos = c.getColumnIndex("running");
            int date_pos = c.getColumnIndex("date");

            info.setPushup(c.getInt(push_up_pos));
            info.setSitup(c.getInt(sit_up_pos));
            info.setRunning(c.getInt(running_pos));
            info.setDate(c.getDate(date_pos));
        }
        db.close();
        return info;
    }
    public static int setUser(Context context, Info info){
        DBhelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", info.name);
        values.put("height", info.height);
        values.put("weight", info.weight);
        values.put("age", info.age);
        values.put("sex", info.sex);
        int newRowId = (int)db.insert(User, null, values);
        db.close();
        return newRowId;
    }  

    public static void updateUser(Context context,int id, Info info){
        DBhelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "UPDATE User SET name=?,"
                    +"height=?,"
                    +"weight=?,"
                    +"age=?,"
                    +"sex=? WHERE id="+id+"";
                    
        String[] value = {info.name, 
                        info.height, 
                        info.weight, 
                        info.age, 
                        info.sex};
        db.execSQL(sql,value);
        db.close();
    }  

    public static void setPushUpRecord(Context context, int id, int repeats){
        DBhelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "INSERT INTO Record(id,"
                    +"push_up,"
                    +"date) VALUES(?,?,?)";
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        String date = sdf.format(new Date());

        String[] value = {Int.toString(id), 
                        Int.toString(repeats),
                        date};
        db.execSQL(sql,value);
        db.close();
    }  

    public static void setSitUpRecord(Context context,int id, int repeats){
        DBhelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();       
        String sql = "INSERT INTO Record(id,"
                    +"sit_up,"
                    +"date) VALUES(?,?,?)";
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        String date = sdf.format(new Date());

        String[] value = {Int.toString(id), 
                        Int.toString(repeats),
                        date};
        db.execSQL(sql,value);
        db.close();
    }  

    public static void setRunniongRecord(Context context,int id, int time){
        DBhelper helper = new DBHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();       
        String sql = "INSERT INTO Record(id,"
                    +"running,"
                    +"date) VALUES(?,?,?)";
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",Locale.getDefault());
        String date = sdf.format(new Date());

        String[] value = {Int.toString(id), 
                        Int.toString(time),
                        date};
        db.execSQL(sql,value);
        db.close();
    }  
}