public class Info{
    public String name;
    public String height;
    public String weight;
    public String age;
    public String sex;
    public String sit_up;
    public String push_up;
    public String running;
    public String date;
    
    public void setName(String name){
    this.name = name;
    }
    
    public void setHeight(int height){
    this.height = Int.toString(height);
    }
    public void setWeight(int weight){
    this.weight = Int.toString(weight);
    }
    
    public void setAge(int age){
    this.age = Int.toString(age);
    }
    
    public void setSex(String sex){
    this.sex = sex;
    }
    
    public void setPushup(int push_up){
    this.push_up = Int.toString(push_up);
    }
    
    public void setSitup(int sit_up){
    this.sit_up = Int.toString(sit_up);
    }
    public void setRunning(int running){
    this.running = Int.toString(running);
    }
    
    public void setDate(Date date){
    SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
    String to = transFormat.format(date);
    this.date = to;
    }
}
    