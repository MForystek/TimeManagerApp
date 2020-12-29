package ApplicationLogic;

public class ActivityFactory {
    public Activity makeActivity(String isDeadline, String isSegmented){
        if(isDeadline.equals("yes") && isSegmented.equals("yes")){
            return new ProjectActivity();
        }else if(isDeadline.equals("yes")){
            return new OneTimeActivity();
        }else if(isSegmented.equals("yes")){
            return new PeriodicActivity();
        }else{
            return new OneTimeActivity();
        }
    }
}
