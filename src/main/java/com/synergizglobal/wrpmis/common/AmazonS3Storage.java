package com.synergizglobal.wrpmis.common;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class AmazonS3Storage {
	
	private static Logger log = Logger.getLogger(AmazonS3Storage.class);
	
	private static final String SUFFIX = "/";
	
	public static void saveFileInS3Bucket(String saveDirectory, MultipartFile multipartFile) throws Exception {
		try {
			// credentials object identifying user for authentication
			// user must have AWSConnector and AmazonS3FullAccess for 
			// this example to work
			/*AWSCredentials credentials = new BasicAWSCredentials(
					"YourAccessKeyID", 
					"YourSecretAccessKey");*/
			
			AWSCredentials credentials = new BasicAWSCredentials(
					"AKIAIKGUGRXOOQP6EB5Q", 
					"J3WQ4rw7oZsa1C+Tuq86H0WQebfEDLsIRdLi/8Jo");
			
			// create a client connection based on credentials
			AmazonS3 s3client = new AmazonS3Client(credentials);
			
			// create bucket - name must be unique for all S3 users
			String bucketName = "syntrack-dev";
			//s3client.createBucket(bucketName);
			
			// list buckets
			for (Bucket bucket : s3client.listBuckets()) {
				//System.out.println("Bucket name - " + bucket.getName());
				//log.error("Bucket name - " + bucket.getName());
			}
			
			// create folder into bucket
			//String folderName = "testfolder";
			
			String folderName = saveDirectory;
			
			createFolder(bucketName, folderName, s3client);
			
			
			String file = multipartFile.getOriginalFilename();
			
			//File newFile = multipartToFile(multipartFile);
			
			// upload file to folder and set it to public
			String fileName = folderName + SUFFIX + file;
			/*s3client.putObject(new PutObjectRequest(bucketName, fileName, 
					new File("C://Users//Synergiz Global//Desktop//guru//Admin//SynTrack Admin Portal FRD.docx"))
					.withCannedAcl(CannedAccessControlList.PublicRead));*/
			
			//s3client.putObject(new PutObjectRequest(bucketName, fileName,newFile).withCannedAcl(CannedAccessControlList.PublicRead));
			
			InputStream is = multipartFile.getInputStream();
		    s3client.putObject(new PutObjectRequest(bucketName,fileName,is,new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead));
			
		    //deleteFolder(bucketName, folderName, s3client);
			
			// deletes bucket
			//s3client.deleteBucket(bucketName);
		} catch (Exception e) {
			throw new Exception(e);
		}
		
	}
	
	public static File multipartToFile(MultipartFile multipart)
	{
		File convFile = null;
		try {

		    convFile = new File( multipart.getOriginalFilename());
		    
		    multipart.transferTo(convFile);
		} catch (Exception e) {
			// TODO: handle exception
		}
	    return convFile;
	}
	
	public static void createFolder(String bucketName, String folderName, AmazonS3 client) throws Exception {
		try {
			// create meta-data for your folder and set content-length to 0
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(0);
			// create empty content
			InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
			// create a PutObjectRequest passing the folder name suffixed by /
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,
					folderName + SUFFIX, emptyContent, metadata);
			// send request to S3 to create folder
			client.putObject(putObjectRequest);
		} catch (Exception e) {
			throw new Exception(e);
		}		
	}
	/**
	 * This method first deletes all the files in given folder and than the
	 * folder itself
	 * @throws Exception 
	 */
	public static void deleteFolder(String bucketName, String folderName, AmazonS3 client) throws Exception {
		try {
			List<S3ObjectSummary> fileList = client.listObjects(bucketName, folderName).getObjectSummaries();
			for (S3ObjectSummary file : fileList) {
				client.deleteObject(bucketName, file.getKey());
			}
			client.deleteObject(bucketName, folderName);
		} catch (Exception e) {
			throw new Exception(e);
		}		
	}
	
	public static S3ObjectInputStream getFileFromS3Bucket(String saveDirectory, String fileName) throws Exception {
		try {
			AWSCredentials credentials = new BasicAWSCredentials(
					"AKIAIKGUGRXOOQP6EB5Q", 
					"J3WQ4rw7oZsa1C+Tuq86H0WQebfEDLsIRdLi/8Jo");
			
			// create a client connection based on credentials
			AmazonS3 s3client = new AmazonS3Client(credentials);
			
			// list buckets
			for (Bucket bucket : s3client.listBuckets()) {
				log.error("Bucket name - " + bucket.getName());
			}
			
			
			/*String existingBucketName = "<your Bucket>";
			  String keyName = "/"+"";*/
			
			  String existingBucketName = "syntrack-dev";
			  String keyName = saveDirectory+"/"+fileName;
			  
			  GetObjectRequest request = new GetObjectRequest(existingBucketName,
			    keyName);
			  S3Object object = s3client.getObject(request);
			  S3ObjectInputStream objectContent = object.getObjectContent();
			  
			  /*String home = System.getProperty("user.home");
			  File file = new File(home+"/" +saveDirectory+"/"+ fileName); 
			  IOUtils.copy(objectContent, new FileOutputStream(file));*/
			  return objectContent;
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public static void saveGeneratedTenderReport(String saveDirectory, byte[] byteArray, String fileName) throws Exception {
		// credentials object identifying user for authentication
		// user must have AWSConnector and AmazonS3FullAccess for 
		// this example to work
		/*AWSCredentials credentials = new BasicAWSCredentials(
				"YourAccessKeyID", 
				"YourSecretAccessKey");*/
		try {
			AWSCredentials credentials = new BasicAWSCredentials(
					"AKIAIKGUGRXOOQP6EB5Q", 
					"J3WQ4rw7oZsa1C+Tuq86H0WQebfEDLsIRdLi/8Jo");
			
			// create a client connection based on credentials
			AmazonS3 s3client = new AmazonS3Client(credentials);
			
			// create bucket - name must be unique for all S3 users
			String bucketName = "syntrack-dev";
			//s3client.createBucket(bucketName);
			
			// list buckets
			for (Bucket bucket : s3client.listBuckets()) {
				//System.out.println("Bucket name - " + bucket.getName());
				//log.error("Bucket name - " + bucket.getName());
			}
			
			// create folder into bucket
			//String folderName = "testfolder";
			
			String folderName = saveDirectory;
			
			createFolder(bucketName, folderName, s3client);
			
			String fn = folderName + SUFFIX + fileName;
			
			//System.out.println("Uploading a file into S3 bucket: {} : "+ fn);
	
	        InputStream inputStream = new ByteArrayInputStream(byteArray);
	
	        Long contentLength = Long.valueOf(byteArray.length);
	
	        ObjectMetadata metadata = new ObjectMetadata();
	        metadata.setContentLength(contentLength);
	
	        //s3client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, metadata));
	    
	
	        s3client.putObject(new PutObjectRequest(bucketName, fn, inputStream, metadata).withCannedAcl(CannedAccessControlList.PublicRead));
		}catch(AmazonClientException ex){
			throw new Exception(ex);
        }
	}
	
	public static List<String> getObjectslistFromFolder(String folderKey) {
		List<String> keys = new ArrayList<>();
		try {			
			AWSCredentials credentials = new BasicAWSCredentials(
					"AKIAIKGUGRXOOQP6EB5Q", 
					"J3WQ4rw7oZsa1C+Tuq86H0WQebfEDLsIRdLi/8Jo");
			
			// create a client connection based on credentials
			AmazonS3 s3client = new AmazonS3Client(credentials);
			
			// create bucket - name must be unique for all S3 users
			String bucketName = "syntrack-dev";
			//s3client.createBucket(bucketName);
			
			// list buckets
			for (Bucket bucket : s3client.listBuckets()) {
				//System.out.println("Bucket name - " + bucket.getName());
				//log.error("Bucket name - " + bucket.getName());
			}			
	   
		   ListObjectsRequest listObjectsRequest = 
		                                new ListObjectsRequest()
		                                      .withBucketName(bucketName)
		                                      .withPrefix(folderKey + "/").withMarker(folderKey + "/");		 
		   
		   ObjectListing objectListing = s3client.listObjects(listObjectsRequest);

		   for (S3ObjectSummary summary : objectListing.getObjectSummaries()) {
		        //System.out.println(summary.getKey());
		        String filePath = summary.getKey();
		    	String[] tokens = filePath.split("/");
		    	String lastToken = tokens[tokens.length-1];
		    	keys.add(lastToken);
		   }
		} catch (Exception e) {
			log.error("getObjectslistFromFolder() : "+e.getMessage());
		}
	    return keys;
	}
	
	public static void saveGeneratedMPRReport(String saveDirectory, byte[] byteArray, String fileName) throws Exception {
		try {
			AWSCredentials credentials = new BasicAWSCredentials(
					"AKIAIKGUGRXOOQP6EB5Q", 
					"J3WQ4rw7oZsa1C+Tuq86H0WQebfEDLsIRdLi/8Jo");
			
			// create a client connection based on credentials
			AmazonS3 s3client = new AmazonS3Client(credentials);
			
			// create bucket - name must be unique for all S3 users
			String bucketName = "syntrack-dev";
			//s3client.createBucket(bucketName);
			
			// list buckets
			for (Bucket bucket : s3client.listBuckets()) {
				//System.out.println("Bucket name - " + bucket.getName());
				//log.error("Bucket name - " + bucket.getName());
			}
			
			// create folder into bucket
			//String folderName = "testfolder";
			
			String folderName = saveDirectory;
			
			createFolder(bucketName, folderName, s3client);
			
			String fn = folderName + SUFFIX + fileName;
			
			//System.out.println("Uploading a file into S3 bucket: {} : "+ fn);
	
	        InputStream inputStream = new ByteArrayInputStream(byteArray);
	
	        Long contentLength = Long.valueOf(byteArray.length);
	
	        ObjectMetadata metadata = new ObjectMetadata();
	        metadata.setContentLength(contentLength);
	
	        //s3client.putObject(new PutObjectRequest(bucketName, fileName, inputStream, metadata));
	    
	
	        s3client.putObject(new PutObjectRequest(bucketName, fn, inputStream, metadata).withCannedAcl(CannedAccessControlList.PublicRead));
		}catch(AmazonClientException ex){
			throw new Exception(ex);
        }
	}
	
	public static List<S3ObjectInputStream> getISObjectslistFromFolder(String folderKey) {
		List<S3ObjectInputStream> objs = new ArrayList<S3ObjectInputStream>();
		try {			
			AWSCredentials credentials = new BasicAWSCredentials(
					"AKIAIKGUGRXOOQP6EB5Q", 
					"J3WQ4rw7oZsa1C+Tuq86H0WQebfEDLsIRdLi/8Jo");
			
			// create a client connection based on credentials
			AmazonS3 s3client = new AmazonS3Client(credentials);
			
			// create bucket - name must be unique for all S3 users
			String bucketName = "syntrack-dev";
			//s3client.createBucket(bucketName);
			
			// list buckets
			for (Bucket bucket : s3client.listBuckets()) {
				//System.out.println("Bucket name - " + bucket.getName());
				//log.error("Bucket name - " + bucket.getName());
			}			
	   
		   ListObjectsRequest listObjectsRequest = 
		                                new ListObjectsRequest()
		                                      .withBucketName(bucketName)
		                                      .withPrefix(folderKey + "/").withMarker(folderKey + "/");		 
		   
		   ObjectListing objectListing = s3client.listObjects(listObjectsRequest);

		   for (S3ObjectSummary summary : objectListing.getObjectSummaries()) {
		        //System.out.println(summary.getKey());
		        String filePath = summary.getKey();
		    	String[] tokens = filePath.split("/");
		    	String lastToken = tokens[tokens.length-1];
		    	//keys.add(lastToken);*/
			   
			   S3ObjectInputStream obj = getFileFromS3Bucket(folderKey, lastToken);
			   if(lastToken.indexOf(".pdf") <= 0) {
				   objs.add(obj);
			   }
		   }
		} catch (Exception e) {
			log.error("getObjectslistFromFolder() : "+e.getMessage());
		}
	    return objs;
	}
	
	
	public static void saveLoginBackgroundImageInS3Bucket(String saveDirectory, MultipartFile multipartFile) throws Exception {
		try {
			AWSCredentials credentials = new BasicAWSCredentials(
					"AKIAIKGUGRXOOQP6EB5Q", 
					"J3WQ4rw7oZsa1C+Tuq86H0WQebfEDLsIRdLi/8Jo");
			
			// create a client connection based on credentials
			AmazonS3 s3client = new AmazonS3Client(credentials);
			
			// create bucket - name must be unique for all S3 users
			String bucketName = "syntrack-dev";
			String folderName = saveDirectory;			
			createFolder(bucketName, folderName, s3client);		
			
			String file = "login-background.jpg";
			String fileName = folderName + SUFFIX + file;
			InputStream is = multipartFile.getInputStream();
		    s3client.putObject(new PutObjectRequest(bucketName,fileName,is,new ObjectMetadata()).withCannedAcl(CannedAccessControlList.PublicRead));
			
		} catch (Exception e) {
			throw new Exception(e);
		}
		
	}
}
