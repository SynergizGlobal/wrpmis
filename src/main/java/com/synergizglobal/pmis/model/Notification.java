package com.synergizglobal.pmis.model;

import java.util.List;

public class Notification {
	private String notificationId,userId,workId,workName,title,description,image,link,readUnreadStatus,closeOpenStatus,closeDateTime,readDateTime,createdDate,notificationLink,timeAgo;
	
	private String activity,activityType,location,boqCompleted,boqTotal,boqUnits,boqCompleteTotal,boqCompleteTotalForMailNotification;
	
	/**
	 * This method get the activity
	 * @return type of this method is activity
	 */
	public String getActivity() {
		return activity;
	}
	/**
	 * This method set the activity
	 * @param activity it is string type variable that hold the activity
	 */
	public void setActivity(String activity) {
		this.activity = activity;
	}
	/**
	 * This method get the activity type 
	 * @return type of this method is activityType
	 */
	public String getActivityType() {
		return activityType;
	}
	/**
	 * This method set the activity type 
	 * @param activityType it is string type variable that hold the activity type
	 */
	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	/**
	 * This method get the location
	 * @return type of this method is location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * This method set the location
	 * @param location it is string type variable that hold the location
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * This method get the BoqCompleted
	 * @return type of this method is boqCompleted
	 */
	public String getBoqCompleted() {
		return boqCompleted;
	}
	/**
	 * This method set the BoqCompleted
	 * @param boqCompleted it is string type variable that hold the boqCompleted
	 */
	public void setBoqCompleted(String boqCompleted) {
		this.boqCompleted = boqCompleted;
	}
	/**
	 * This method get the BoqTotal
	 * @return type of this method is boqTotal
	 */
	public String getBoqTotal() {
		return boqTotal;
	}
	/**
	 * This method set the BoqTotal
	 * @param boqTotal it is string type variable that hold the boqTotal
	 */
	public void setBoqTotal(String boqTotal) {
		this.boqTotal = boqTotal;
	}
	/**
	 * This method set the boqUnits
	 * @return type of this method is boqUnits
	 */
	public String getBoqUnits() {
		return boqUnits;
	}
	/**
	 * This method set the Boq units
	 * @param boqUnits it is string type variable that hold the boqUnits
	 */
	public void setBoqUnits(String boqUnits) {
		this.boqUnits = boqUnits;
	}
	/**
	 * This method get the BoqCcompleteTotal
	 * @return type of this method is boqCompleteTotal
	 */
	public String getBoqCompleteTotal() {
		return boqCompleteTotal;
	}
	/**
	 * This method set the BoqCompleteTotal
	 * @param boqCompleteTotal it is string type variable that hold the boqCompleteTotal
	 */
	public void setBoqCompleteTotal(String boqCompleteTotal) {
		this.boqCompleteTotal = boqCompleteTotal;
	}
	/**
	 * This method get the BoqCompleteTotalForMailNotification
	 * @return type of this method is boqCompleteTotalForMailNotification
	 */
	public String getBoqCompleteTotalForMailNotification() {
		return boqCompleteTotalForMailNotification;
	}
	/**
	 * This method set the boqCompleteTotalForMailNotification
	 * @param boqCompleteTotalForMailNotification it is string type variable that hold the boqCompleteTotalForMailNotification
	 */
	public void setBoqCompleteTotalForMailNotification(String boqCompleteTotalForMailNotification) {
		this.boqCompleteTotalForMailNotification = boqCompleteTotalForMailNotification;
	}
	
	private List<Notification> dueActivities;
	
	/**
	 * This method get the work id
	 * @return type of this method is workId
	 */
	public String getWorkId() {
		return workId;
	}
	/**
	 * This method set the work id
	 * @param workId it is string type variable that hold the work id
	 */
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	/**
	 * This method get the work name
	 * @return type of this method is workName
	 */
	public String getWorkName() {
		return workName;
	}
	/**
	 * This method set the work name
	 * @param workName it is string type variable that hold the work name
	 */
	public void setWorkName(String workName) {
		this.workName = workName;
	}
	/**
	 * This method get the notification id
	 * @return type of this method is notficationId
	 */
	public String getNotificationId() {
		return notificationId;
	}
	/**
	 * This method set the notificationId
	 * @param notificationId it is string type variable that hold the  notification id
	 */
	public void setNotificationId(String notificationId) {
		this.notificationId = notificationId;
	}
	
	/**
	 * This method get the user id
	 * @return type of this method is userId
	 */
	public String getUserId() {
		return userId;
	}
	/**
	 * This method set the user id
	 * @param userId it is string type variable that hold the userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	/**
	 * This method get the title
	 * @return type of this method is title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * This method set the title
	 * @param title it is string type variable that hold the title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * This method get the description
	 * @return type of this method is description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * This method set the description
	 * @param description it is string type variable that hold the description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * This method get the image 
	 * @return type of this method is image
	 */
	public String getImage() {
		return image;
	}
	/**
	 * This method set the image 
	 * @param image it is string type variable that hold the image
	 */
	public void setImage(String image) {
		this.image = image;
	}
	/**
	 * This method get the link
	 * @return type of this method is link
	 */
	public String getLink() {
		return link;
	}
	/**
	 * This method set the link
	 * @param link it is string type variable that hold the link
	 */
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * This method get the read unread status
	 * @return type of this method is readUnreadStatus
	 */
	public String getReadUnreadStatus() {
		return readUnreadStatus;
	}
	/**
	 * This method set the read unread status
	 * @param readUnreadStatus it is string type variable that hold the status
	 */
	public void setReadUnreadStatus(String readUnreadStatus) {
		this.readUnreadStatus = readUnreadStatus;
	}
	/**
	 * This method get the close open status
	 * @return type of this method is close open status
	 */
	public String getCloseOpenStatus() {
		return closeOpenStatus;
	}
	/**
	 * This method set the close open status
	 * @param closeOpenStatus it is string type variable that hold the close open status
	 */
	public void setCloseOpenStatus(String closeOpenStatus) {
		this.closeOpenStatus = closeOpenStatus;
	}
	/**
	 * This method get the read date time
	 * @return type of this method is readDateTime
	 */
	public String getReadDateTime() {
		return readDateTime;
	}
	/**
	 * This method set the read date time
	 * @param readDateTime it is string type variable that hold the read date time
	 */
	public void setReadDateTime(String readDateTime) {
		this.readDateTime = readDateTime;
	}
	/**
	 * This method get the created date
	 * @return type of this createdDate
	 */
	public String getCreatedDate() {
		return createdDate;
	}
	/**
	 * This method set the created date
	 * @param createdDate it is string type variable that hold the created date
	 */
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	/**
	 * This method  get the notification link
	 * @return type of this method is notificationLink
	 */
	public String getNotificationLink() {
		return notificationLink;
	}
	/**
	 * This method set the notificationLink
	 * @param notificationLink it is string type variable that hold the notificationLink
	 */
	public void setNotificationLink(String notificationLink) {
		this.notificationLink = notificationLink;
	}
	/**
	 * This method get the due activities
	 * @return type of this method is dueActivities
	 */
	public List<Notification> getDueActivities() {
		return dueActivities;
	}
	/**
	 * This method set the due activities
	 * @param dueActivities it is List type object that hold the dueActivities
	 */
	public void setDueActivities(List<Notification> dueActivities) {
		this.dueActivities = dueActivities;
	}
	/**
	 * This method get the timeAgo
	 * @return type of this method is timeAgo
	 */
	public String getTimeAgo() {
		return timeAgo;
	}
	/**
	 * This method set the TimeAgo
	 * @param timeAgo it is string type variable that hold the timeAgo
	 */
	public void setTimeAgo(String timeAgo) {
		this.timeAgo = timeAgo;
	}
	/**
	 * This method get the close date time
	 * @return type of this method is closeDateTime
	 */
	public String getCloseDateTime() {
		return closeDateTime;
	}
	/**
	 * This method set the closeDateTime
	 * @param closeDateTime it is string type variable that hold the closeDateTime
	 */
	public void setCloseDateTime(String closeDateTime) {
		this.closeDateTime = closeDateTime;
	}
	
}
