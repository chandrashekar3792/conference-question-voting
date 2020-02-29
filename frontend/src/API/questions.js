import axios from "axios";
import constants from "../config/constants";
axios.defaults.baseURL = constants.apiUrl;
export const voteQuestion=(id,choiceId)=>{
  const requestUrl = `api/v1/questions/${id}/vote?choiceId=${choiceId}`;
  return axios.put(requestUrl, {});
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