package kr.co.softcampus.tooksampoom;

public class UserInfo {
        public String name;
        public String height;
        public String weight;
        public String age;
        public String sex;

        public void setName(String name) {
            this.name = name;
        }

        public void setHeight(int height) {
            this.height = Integer.toString(height);
        }

        public void setWeight(int weight) {
            this.weight = Integer.toString(weight);
        }

        public void setAge(int age) {
            this.age = Integer.toString(age);
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }

