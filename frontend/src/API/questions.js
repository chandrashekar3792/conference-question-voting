import axios from "axios";
import constants from "../config/constants";
axios.defaults.baseURL = constants.apiUrl;
export const voteQuestion=(id)=>{
  const requestUrl = `api/v1/questions/${id}/vote`;
  return axios.put(requestUrl, {});
}

export const addQuestion=(title,category)=>{
  try{
    const requestUrl = "api/v1/questions";
    return axios.post(requestUrl, {title,category});
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