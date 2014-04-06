package com.temporun.dao;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.temporun.dto.*;

public interface INetworkUser {

	/**
	 * Method to get all users back from the database
	 * @throws Exception
	 * @return List<User> A list of all user objects
	 */
	public ArrayList<User> getAllUsers() throws Exception;
	
	
	/**
	 * Method to update a truck's login password
	 * 
	 * @param userID the userID of the user whose password we are updating
	 * @param newPW their new password
	 * @throws Exception
	 *             Exception is thrown if there is an issue communicating over
	 *             the network or writing to the database
	 */

	public void updatePassword(int userID, String newPW)
			throws Exception;
	
	/**
	 * Method to add a new user to the database
	 * @param username
	 * @throws URISyntaxException 
	 * @throws IOException 
	 * @throws ConnectException 
	 * @throws Exception 
	 */
	public void addUser(String username, String password) throws URISyntaxException, ConnectException, IOException, Exception;

}

