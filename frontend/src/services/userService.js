import axios from 'axios';

const API_URL = 'http://localhost:8080/users';

// ✅ Signup new user
export const signup = (user) => axios.post(`${API_URL}/signup`, user);

// ✅ Login existing user
export const login = (user) => axios.post(`${API_URL}/login`, user);

// ✅ Get all users (admin use)
export const getAllUsers = () => axios.get(API_URL);

// ✅ Delete user by ID
export const deleteUser = (id) => axios.delete(`${API_URL}/${id}`);

// ✅ Get user profile by email (stored in localStorage after login)
export const getUserProfile = () => {
  const user = JSON.parse(localStorage.getItem("user")); // stored during login
  const email = user?.email;

  if (!email) {
    throw new Error("Email not found in localStorage.");
  }

  return axios.get(`${API_URL}/profile`, {
    params: { email }  // will send as /users/profile?email=...
  });
};
// ✅ Get user's adoption requests using email
export const getUserAdoptionRequests = () => {
  const user = JSON.parse(localStorage.getItem("user"));
  const email = user?.email;

  if (!email) {
    throw new Error("Email not found in localStorage.");
  }

  return axios.get(`http://localhost:8080/users/${email}/requests`);
};