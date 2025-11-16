import type { Dispatch, SetStateAction } from 'react';
import type { ViewMode } from '../types/types';
import { UserIcon, LockIcon } from '../components/Icons';
import adminIllustration from '../assets/admin.jpg';

interface AdminLoginProps {
  setViewMode: Dispatch<SetStateAction<ViewMode>>;
}

const AdminLogin = ({ setViewMode }: AdminLoginProps) => {
  return (
    <>
      {/* --- BLUE LEFT PANEL --- */}
      <div className="form-panel blue-panel">
        <h1>HealthCare +</h1>
        <h1>Admin Portal</h1>
        <p><i>Please enter your admin credentials.</i></p>
        <img src={adminIllustration} alt="Admin" className="panel-image" />
      </div>

      {/* --- WHITE RIGHT PANEL --- */}
      <div className="form-panel white-panel">
        <div className="white-panel-header">
          <p>
            Are you a Patient?
            <span onClick={() => setViewMode('patientSignIn')} className="toggle-link">Patient Login</span>
          </p>
        </div>

        <div className="form-content">
          <h2 className="form-title">Admin Log In</h2>
          <form>
            {/* Admin "User Name" field */}
            <div className="input-group">
              <span className="icon"><UserIcon /></span>
              <input type="text" placeholder="User Name" />
            </div>
            
            {/* Password field */}
            <div className="input-group">
              <span className="icon"><LockIcon /></span>
              <input type="password" placeholder="Password" />
            </div>
            
            <button type="submit" className="form-button">
              SIGN IN
            </button>
          </form>
          <p className="footer-text">
            Access is restricted to authorized users.
          </p>
        </div>
      </div>
    </>
  );
};

export default AdminLogin;