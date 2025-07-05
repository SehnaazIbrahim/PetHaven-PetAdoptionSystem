import axios from 'axios';

const API_URL = 'http://localhost:8080/adoptions';

export const getAllRequests = () => axios.get(API_URL);
export const createRequest = (request) => axios.post(API_URL, request);
export const updateRequest = (id, status) =>
  axios.put(`${API_URL}/${id}`, { status });
export const deleteRequest = (id) => axios.delete(`${API_URL}/${id}`);


export const getUserRequests = (email) => 
  axios.get(`${API_URL}/user/requests`, {
    params: { email }
  });