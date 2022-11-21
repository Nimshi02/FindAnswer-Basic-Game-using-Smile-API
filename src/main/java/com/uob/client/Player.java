package com.uob.client;

public class Player {
	static int score;
	static  int level;
	static int nextround[] = new int[10];
	static int count = 0;
	 private String username;
	    private String email;
	    private String password;
	
	public Player()
	{
		
	}
	public Player(String username,String email,String password)
	{
		this.username=username;
		this.email=email;
		this.password=password;
	}


	public int getLevel() {
		return level;
	}
	public void setLevel() {
		level++;
	}

	public int setvalue(int answer)
	{
		nextround[count]=answer;
		count++;
		return count;
	}
	public int getScore()
	{
		return score;
	}
	public int getFirstAnswer()
	{
		return nextround[count-2];
	}
	public int getSecondAnswer()
	{
		return nextround[count-1];
	}
	public void setscore()
	{
		Player.score+=10;
	}
	 public String getUsername() {
	        return username;
	    }

	    /*
	    Get the Email of the user
	    */
	    public String getEmail() {
	        return email;
	    }

	    /*
	    Get the password of the user
	    */
	    public String getPassword() {
	        return password;
	    }
}
