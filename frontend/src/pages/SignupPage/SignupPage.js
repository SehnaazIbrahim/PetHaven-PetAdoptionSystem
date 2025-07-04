import React, { useState } from 'react';
import { signup } from '../../services/userService';
import { useNavigate } from 'react-router-dom';
import './SignupPage.css';

const SignupPage = () => {
  const [data, setData] = useState({ name: '', email: '', password: '' });
  const navigate = useNavigate();

  const handleChange = (e) => {
    setData({ ...data, [e.target.name]: e.target.value });
  };

  const handleSignup = async (e) => {
    e.preventDefault();
    try {
      await signup(data);
      alert('Signup successful! Please login.');
      navigate('/login');
    } catch (err) {
      alert('Signup failed. Try again.');
    }
  };

  return (
    <div className="signup-page">
      <form className="signup-form" onSubmit={handleSignup}>
        <h2>Signup</h2>
        <input
          type="text"
          name="name"
          placeholder="Name"
          required
          value={data.name}
          onChange={handleChange}
        />
        <input
          type="email"
          name="email"
          placeholder="Email"
          required
          value={data.email}
          onChange={handleChange}
        />
        <input
          type="password"
          name="password"
          placeholder="Password"
          required
          value={data.password}
          onChange={handleChange}
        />
        <button type="submit">Register</button>
      </form>
    </div>
  );
};

export default SignupPage;
