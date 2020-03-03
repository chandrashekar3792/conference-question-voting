import axios from "axios";
import constants from "../config/constants";
axios.defaults.baseURL = constants.apiUrl;
export const voteQuestion=(id,choiceId)=>{
  let user_id=localStorage.getItem("userid");
  const requestUrl = `api/v1/votes?choiceId=${choiceId}&&questionId=${id}&&userId=${user_id}`;
  return axios.post(requestUrl, {});
}

export const addQuestion=(title,category,options)=>{
  try{
    const requestUrl = "api/v1/questions";
    let choices=Object.values(options).filter(item=>{if(item)return item;})
    return axios.post(requestUrl, {title,category,choices});
  }catch (e) {
    throw e;
  }
}
export const getQuestions=()=>{
    try{
      return axios.get('api/v1/questions')
    }catch (e) {
      throw e;
    }
}

export const RegisterAPI=(email)=>{
  try{
    const requestUrl = "api/v1/users";
    return axios.post(requestUrl, {email});
  }catch (e) {
    throw e;
  }
}
export const getChoices=()=>{
  const requestUrl="api/v1/choices";
  return axios.get(requestUrl);
}