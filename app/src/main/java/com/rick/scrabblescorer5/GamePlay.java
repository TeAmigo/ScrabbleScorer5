package com.rick.scrabblescorer5;

public class GamePlay {

    public static final String TABLE_NAME = "GamePlay";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_GAMEID = "gameid";
    public static final String COLUMN_PLAYNUMBER = "playnumber";
    public static final String COLUMN_PLAYER = "player";
    public static final String COLUMN_POINTS = "points";

    public int getPlaynumber() {
        return playnumber;
    }

    public void setPlaynumber(int playnumber) {
        this.playnumber = playnumber;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }




    private int id;
    private int gameid;
    private int playnumber;
    private String player;
    private int points;

    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + COLUMN_GAMEID + " INTEGER,"
                    + COLUMN_PLAYER + " TEXT, "
                    + COLUMN_PLAYNUMBER + " INTEGER, "
                    + COLUMN_POINTS + " INTEGER,"
                    + "FOREIGN KEY(" + COLUMN_GAMEID + ") REFERENCES GameTop(id) "
                    + ")";

    public GamePlay() {
    }

    public String getCreate() {
        return GamePlay.CREATE_TABLE;
    }

    public int getGameid() {
        return gameid;
    }

    public void setGameid(int gameid) {
        this.gameid = gameid;
    }

    public GamePlay(int id, int gameid, int playnumber, String player, int points) {
        this.id = id;
        this.gameid = gameid;
        this.playnumber = playnumber;
        this.player = player;
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



}
