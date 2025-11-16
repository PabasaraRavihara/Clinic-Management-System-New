import React, { useState } from 'react';
import './App.css';

// Import Types
import { ViewMode } from './types/types';

// Import Sidebar Icons
import { SignInIcon, SignUpIcon, DoctorIcon } from './components/Icons';

// Import View Components
import PatientSignIn from './views/PatientSignIn';
import PatientSignUp from './views/PatientSignUp';
import DoctorLogin from './views/DoctorLogin';
import AdminLogin from './views/AdminLogin';

function App() {
  const [viewMode, setViewMode] = useState<ViewMode>('patientSignIn');

  // This class is still needed for the sign-up/sign-in animation
  const isSignInMode = viewMode !== 'patientSignUp';

  // Helper function to render the correct view
  const renderView = () => {
    switch (viewMode) {
      case 'patientSignIn':
        return <PatientSignIn setViewMode={setViewMode} />;
      case 'patientSignUp':
        return <PatientSignUp setViewMode={setViewMode} />;
      case 'doctorLogin':
        return <DoctorLogin setViewMode={setViewMode} />;
      case 'adminLogin':
        return <AdminLogin setViewMode={setViewMode} />;
      default:
        return <PatientSignIn setViewMode={setViewMode} />;
    }
  };

  return (
    <div className="app-wrapper">
      
      {/* --- FAR LEFT SIDEBAR --- */}
      <div className="sidebar">
        <div 
          className={`sidebar-icon ${viewMode === 'patientSignIn' ? 'active' : ''}`}
          onClick={() => setViewMode('patientSignIn')}
        >
          <SignInIcon />
          <span>Sign In</span>
        </div>
        
        <div 
          className={`sidebar-icon ${viewMode === 'patientSignUp' ? 'active' : ''}`}
          onClick={() => setViewMode('patientSignUp')}
        >
          <SignUpIcon />
          <span>Sign Up</span>
        </div>

        <div 
          className={`sidebar-icon ${viewMode === 'doctorLogin' || viewMode === 'adminLogin' ? 'active' : ''}`}
          onClick={() => setViewMode('doctorLogin')}
        >
          <DoctorIcon />
          <span>Doctor log In</span>
        </div>
      </div>

      {/* --- MAIN CONTENT AREA --- */}
      <div className={`main-container ${isSignInMode ? 'sign-in-mode' : ''}`}>
        
        {/* Render the active view component */}
        {renderView()}

      </div>
    </div>
  );
}

export default App;