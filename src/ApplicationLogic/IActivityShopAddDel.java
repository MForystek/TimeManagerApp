package ApplicationLogic;

import ApplicationLogic.Activity;

public interface IActivityShopAddDel {
    int delActivityFromShop(String activityName);
    int addActivityToShop(Activity activity);
}
