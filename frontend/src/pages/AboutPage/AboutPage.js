import React from 'react';
import './AboutPage.css';

const AboutPage = () => {
  return (
    <div className="home-page">
      <div className="hero-section">
        <h1 className="home-head">About PetAdopt</h1>
        <p className="home-head">
          Helping animals find loving homes, one adoption at a time 🐶🐱
        </p>
      </div>

      <div className="about-content">
        <p>
          PetAdopt is a platform dedicated to connecting kind-hearted individuals
          with lovable pets in need of a home. Whether you're looking to adopt a
          furry friend or just browse, we've got tails wagging!
        </p>
        <p>
          Our mission is to reduce the number of homeless animals by promoting
          responsible pet adoption. Join us and make a difference today.
        </p>
      </div>
    </div>
  );
};

export default AboutPage;
