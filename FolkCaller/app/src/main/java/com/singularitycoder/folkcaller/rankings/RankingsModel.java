package com.singularitycoder.folkcaller.rankings;

public class RankingsModel {

    String strRank;
    int intRankUserPic;
    String strUserName;
    String strConversions;

    public RankingsModel(int intRankUserPic, String strUserName, String strRank, String strConversions) {
        this.intRankUserPic = intRankUserPic;
        this.strUserName = strUserName;
        this.strRank = strRank;
        this.strConversions = strConversions;
    }

    public String getStrRank() {
        return strRank;
    }

    public void setStrRank(String strRank) {
        this.strRank = strRank;
    }

    public int getIntRankUserPic() {
        return intRankUserPic;
    }

    public void setIntRankUserPic(int intRankUserPic) {
        this.intRankUserPic = intRankUserPic;
    }

    public String getStrUserName() {
        return strUserName;
    }

    public void setStrUserName(String strUserName) {
        this.strUserName = strUserName;
    }

    public String getStrConversions() {
        return strConversions;
    }

    public void setStrConversions(String strConversions) {
        this.strConversions = strConversions;
    }
}
