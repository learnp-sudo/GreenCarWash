package com.green.car.wash.company.admin.WrapperModel;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.green.car.wash.company.admin.model.Ratings;

//This is a wrapper class to get ratings of specific washer
public class WasherRatings {
    //private String WasherID;
    private String WasherName;
    private String WasherEmailID;
    private List<Ratings> Ratings;
    public WasherRatings()
    {

    }
	public WasherRatings(String washerName, String washerEmailID,
			List<com.green.car.wash.company.admin.model.Ratings> ratings) {
		super();
		this.WasherName = washerName;
		this.WasherEmailID = washerEmailID;
		this.Ratings = ratings;
	}
	public String getWasherName() {
		return WasherName;
	}
	public void setWasherName(String washerName) {
		this.WasherName = washerName;
	}
	public String getWasherEmailID() {
		return WasherEmailID;
	}
	public void setWasherEmailID(String washerEmailID) {
		this.WasherEmailID = washerEmailID;
	}
	public List<Ratings> getRatings() {
		return Ratings;
	}
	public void setRatings(List<Ratings> ratings) {
		this.Ratings = ratings;
	}
	@Override
	public String toString() {
		return "WasherRatings [WasherName=" + WasherName + ", WasherEmailID=" + WasherEmailID + ", Ratings=" + Ratings
				+ "]";
	}



}