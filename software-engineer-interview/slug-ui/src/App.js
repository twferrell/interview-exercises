import React from 'react';
import './index.css';

import Form from './components/Form';

const App = () => (
  <div className="container">
    <h2>Create New Slug</h2>
    <p className="instructions">
      Enter details below and submit to create a new slug
    </p>
    <Form />
  </div>
);

export default App;
