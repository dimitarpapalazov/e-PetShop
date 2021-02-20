import axios from "axios";

const MemberService = {
    registration: (username,email,password,repeat,firstName,lastName) => {
        let params = "?username="+ username+
            "&email="+email+
            "&password="+password+
            "&repeat="+repeat+
            "&firstName="+firstName+
            "&lastName="+lastName;
        return axios.get("/api/members/registration"+ params);
    },
    login: (username, passwod) => {
        return axios.get("/api/members/login?username="+username + "&password="+passwod);
    }

};

export default MemberService;