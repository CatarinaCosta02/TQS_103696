import React, { useEffect, useState } from "react";

const Cache = () => {
  const [data, setData] = useState(null);

  const getCache = async () => {
    await fetch(`http://localhost:8080/api/cache`, {
    })
      .then((res) => {
        if (res.status === 200) return res.json();
      })
      .then((data) => {
        setData(data);
      });
  };

  useEffect(() => {
    getCache();
  }, []);

  return (
    <div>
      <div className="overflow-x-auto">
        <table className="table w-full">
          <thead>
            <tr>
              <th>Number Requests</th>
              <th>Number Hits</th>
              <th>Number Misses</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>
                <span id="numRequest">{data ? data.numRequests : "-"}</span>
              </td>
              <td>
                <span id="numHits">{data ? data.hitCount : "-"}</span>
              </td>
              <td>
                <span id="numMisses">{data ? data.missCount : "-"}</span>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default Cache;