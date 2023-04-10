import React, { useRef, useState } from 'react';

const Search = () => {
    const [data, setData] = useState(false);
    const [loading, setLoading] = useState(false);
    const cityInput = useRef();
    const submitCity = async () => {
        const city = cityInput.current.value;
        if (city) {
            setLoading(true);
            await fetch(`http://localhost:8080/api/forecast/${city}`)
                .then((res) => {
                    if (res.status === 200)
                        return res.json();
                })
                .then(data => {
                    console.log(data);
                    setData(data);
                    setLoading(false);
                })
                .catch(() => setLoading(false));
        }
    }
    return (
        <><div className="hero min-h-700 bg-base-200">
            <div className="content-start text-center">
                <div className="max-w-md">
                    <h1 className="text-5xl font-bold ">Choose a Location</h1>
                    <div className="form-control mt-6 hero-content place-content-center-start">
                        <div className="input-group">
                            <input type="search" ref={cityInput} placeholder="Search…" className="input input-bordered" />
                            <button id="city" className="btn btn-square" onClick={submitCity}>
                                <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" /></svg>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
            <div>
                <div className="overflow-x-auto">
                    <table className="table w-full">
                        <thead>
                            <tr>
                                <th>city_name</th>
                                <th>lat</th>
                                <th>lon</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <span>{data ? data.city_name : "-"}</span>
                                </td>
                                <td>
                                    <span>{data ? data.lat : "-"}</span>
                                </td>
                                <td>
                                    <span>{data ? data.lon : "-"}</span>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div><div>
                <div className="overflow-x-auto">
                    <table className="table w-full">
                        <thead>
                            <tr>
                                <th>aqi</th>
                                <th>O3</th>
                                <th>SO2</th>
                                <th>NO2</th>
                                <th>PM10</th>
                                <th>PM25</th>
                            </tr>
                        </thead>
                        <tbody>
                            {data && data.data.map((item, index) => (
                                <tr key={index}>
                                    <td>
                                        <span>{item.aqi}</span>
                                    </td>
                                    <td>
                                        <span>{item.o3}</span>
                                    </td>
                                    <td>
                                        <span>{item.so2}</span>
                                    </td>
                                    <td>
                                        <span>{item.no2}</span>
                                    </td>
                                    <td>
                                        <span>{item.pm10}</span>
                                    </td>
                                    <td>
                                        <span>{item.pm25}</span>
                                    </td>
                                </tr>
                            ))}

                        </tbody>
                    </table>
                </div>
            </div>
            <div>
                <div className="overflow-x-auto">
                    <table className="table w-full">
                        <thead>
                            <tr>
                                <th>Pollen Level Tree</th>
                                <th>Pollen Level Grass</th>
                                <th>Pollen Level Weeb</th>
                                <th>Mold Level</th>
                            </tr>
                        </thead>
                        <tbody>
                            {data && data.data.map((item, index) => (
                                <tr key={index}>
                                    <td>
                                        <span>{item.pollen_level_tree}</span>
                                    </td>
                                    <td>
                                        <span>{item.pollen_level_grass}</span>
                                    </td>
                                    <td>
                                        <span>{item.pollen_level_weed}</span>
                                    </td>
                                    <td>
                                        <span>{item.mold_level}</span>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            </div></>
    );
}

export default Search;