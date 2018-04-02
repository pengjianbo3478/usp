var FileUtils = {
		videoTypeRegexp : /(mp4)|(avi)|(flv)|(mkv)|(rm)|(rmvb)|(wmv)|(mov)|(3gp)|(asf)|(wmv)|(swf)|(mpg)|(mpeg)|(m2v)|(ts)|(mkv)|(mp3)|(wav)|(aac)|(amr)|(ogg)|(wma)|(ape)/i,
		imageTypeRegexp : /(png)|(jpg)|(jpeg)|(ico)|(bmp)/i,
//		coursewareTypeRegexp : /(png)|(jpg)|(jpeg)|(ico)|(bmp)|(ppt)|(pptx)/i,
		coursewareTypeRegexp : /(ppt)|(pptx)/i,
		validFileType : function(filePath,regexp){
			if(regexp instanceof RegExp){
				var tail = filePath.substr(filePath.lastIndexOf(".")+1);
				if(tail.match(regexp)){
					return true;
				}
			}
			return false;
		}
}
