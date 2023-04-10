import React, {useState} from 'react';
import { Link } from 'react-router-dom';
function Navbar() {
    return (
        <div className="navbar bg-neutral text-neutral-content">
            <div className="flex-1">
                <a className="btn btn-ghost normal-case text-xl">TQS AirQuality</a>
            </div>
            <div className="flex-none">
                <ul className="menu menu-horizontal px-1">
                    <li><a href='http://localhost:3000/current'>Current</a></li>
                    <li><a href='http://localhost:3000/forecast'>Forecast</a></li>
                    <li><a href='http://localhost:3000/cache'>Cache</a></li>
                </ul>
            </div>
        </div>

    );
}

export default Navbar;