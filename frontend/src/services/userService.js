import axios from 'axios';

const API_URL = 'http://localhost:8080/users';

export const signup = (user) => axios.post(`${API_URL}/signup`, user);
export const login = (user) => axios.post(`${API_URL}/login`, user);
export const getAllUsers = () => axios.get(API_URL);
export const deleteUser = (id) => axios.delete(`${API_URL}/${id}`);

// ✅ Get user profile with adopted pets
export const getUserProfile = () => {
  const userId = localStorage.getItem("userId");
  return axios.get(`${API_URL}/profile/${userId}`);
};
