package com.lelocabdriver.login;

import java.io.Serializable;

/**
 * Created by ashish on 02-05-2017.
 */

public class LoginResponseModel implements Serializable {
    public String Picture;
    public String ContactNo2;
    public String LicenseNo;
    public String Password;
    public String IdNumber;
    public String FatherName;
    public String IdType;
    public String ContactNo;
    public String Pincode;
    public String Name;
    public String Email;
    public String Address;
    public String DeviceToken;
    public String Id;
    public VehicleDetail VehicleDetail;
    public String RCNo;

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public String getContactNo2() {
        return ContactNo2;
    }

    public void setContactNo2(String contactNo2) {
        ContactNo2 = contactNo2;
    }

    public String getLicenseNo() {
        return LicenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        LicenseNo = licenseNo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        IdNumber = idNumber;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public String getIdType() {
        return IdType;
    }

    public void setIdType(String idType) {
        IdType = idType;
    }

    public String getContactNo() {
        return ContactNo;
    }

    public void setContactNo(String contactNo) {
        ContactNo = contactNo;
    }

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDeviceToken() {
        return DeviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        DeviceToken = deviceToken;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public VehicleDetail getVehicleDetail() {
        return VehicleDetail;
    }

    public void setVehicleDetail(VehicleDetail vehicleDetail) {
        VehicleDetail = vehicleDetail;
    }

    public String getRCNo() {
        return RCNo;
    }

    public void setRCNo(String RCNo) {
        this.RCNo = RCNo;
    }

    public class VehicleDetail implements Serializable{
        private String Name;
        private String VehicleTypeName;
        private String VehicleId;
        private String PerMinCharge;
        private String VehicleNo;
        private String RideFare;
        private String BaseFare;
        private String VehicleType;
        private String VehicleTypeId;

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getVehicleTypeName() {
            return VehicleTypeName;
        }

        public void setVehicleTypeName(String vehicleTypeName) {
            VehicleTypeName = vehicleTypeName;
        }

        public String getVehicleId() {
            return VehicleId;
        }

        public void setVehicleId(String vehicleId) {
            VehicleId = vehicleId;
        }

        public String getPerMinCharge() {
            return PerMinCharge;
        }

        public void setPerMinCharge(String perMinCharge) {
            PerMinCharge = perMinCharge;
        }

        public String getVehicleNo() {
            return VehicleNo;
        }

        public void setVehicleNo(String vehicleNo) {
            VehicleNo = vehicleNo;
        }

        public String getRideFare() {
            return RideFare;
        }

        public void setRideFare(String rideFare) {
            RideFare = rideFare;
        }

        public String getBaseFare() {
            return BaseFare;
        }

        public void setBaseFare(String baseFare) {
            BaseFare = baseFare;
        }

        public String getVehicleType() {
            return VehicleType;
        }

        public void setVehicleType(String vehicleType) {
            VehicleType = vehicleType;
        }

        public String getVehicleTypeId() {
            return VehicleTypeId;
        }

        public void setVehicleTypeId(String vehicleTypeId) {
            VehicleTypeId = vehicleTypeId;
        }
    }
}
