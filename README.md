Plugin used to display custom alert in android



#how to call plugin for disply success message

 cordova.plugins.customalert.showDialog(function(success){
        console.log("SUCCESS");
    },function(error){
        console.log("ERROR");
    },"Title","Message","Button Text","success");
  }
  
  
  
#how to call plugin for disply error message

 cordova.plugins.customalert.showDialog(function(success){
        console.log("SUCCESS");
    },function(error){
        console.log("ERROR");
    },"Title","Message","Button Text","error");
  }