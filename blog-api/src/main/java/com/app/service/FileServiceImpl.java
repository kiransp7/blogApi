package com.app.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;


@Service
@Transactional
public class FileServiceImpl  implements IFileService{

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		System.out.println("-------------------------------->>>>>>>>>>>>>>>>>>.");
		//File Name
		String name = file.getOriginalFilename();
		
		System.out.println("Original File Name :- " +name);
		
		//Random Generate File Name
		String randomID = UUID.randomUUID().toString();
		
		System.out.println("Random ID :- " +randomID);
		
		String fileName = randomID.concat(name.substring(name.lastIndexOf(".")));
		
		System.out.println("Concat with .  :- " +randomID.concat(name.substring(name.lastIndexOf("."))));
		
		//FullPath
		String filePath = path + File.separator + fileName;
		
		System.out.println("path :- "+path+ "  ||  File Separator :- "+File.separator+ "  ||  fileName :- "+ fileName+ "  || file Path :-  "+filePath);
		System.out.println("-------------------------------->>>>>>>>>>>>>>>>>>.");
				
		//Create Folder if not Created
		File f = new File(path);
		if(!f.exists()) {
				f.mkdir();
		}
		
		//File Copy
		Files.copy(file.getInputStream(), Paths.get(filePath));
		
		return fileName;
	}
	
	
	
	@Override
	public InputStream getResourse(String path, String fileName) throws FileNotFoundException {
		
		String fullPath = path + File.separator + fileName;
		InputStream iStream = new FileInputStream(fullPath);
		return iStream;
	}
	
	
	
	
	
	
	
	

}
