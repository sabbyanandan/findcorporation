package com.example.findcorporation;

public class DownloadInfo {

	private String ipAddress;
	private String hostname;
	private String organization;
	private String country;
	private String zipcode;
	private String username;
	private String date;
	private String pathInformation;
	private String userAgent;
	private String releaseNumber;

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ip_address) {
		this.ipAddress = ip_address;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getPathInformation() {
		return pathInformation;
	}

	public void setPathInformation(String path_information) {
		this.pathInformation = path_information;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String user_agent) {
		this.userAgent = user_agent;
	}

	public String getReleaseNumber() {
		return releaseNumber;
	}

	public void setReleaseNumber(String release_number) {
		this.releaseNumber = release_number;
	}

	@Override public String toString() {
		return "DownloadInfo{" +
				"ipAddress='" + ipAddress + '\'' +
				", hostname='" + hostname + '\'' +
				", organization='" + organization + '\'' +
				", country='" + country + '\'' +
				", zipcode='" + zipcode + '\'' +
				", username='" + username + '\'' +
				", date='" + date + '\'' +
				", pathInformation='" + pathInformation + '\'' +
				", userAgent='" + userAgent + '\'' +
				", releaseNumber='" + releaseNumber + '\'' +
				'}';
	}
}
