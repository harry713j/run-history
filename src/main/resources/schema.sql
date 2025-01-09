CREATE TABLE IF NOT EXISTS run(
    run_id INT NOT NULL,
    title VARCHAR(255) NOT NULL,
    started_on TIMESTAMP NOT NULL,
    finished_on TIMESTAMP NOT NULL,
    miles INT NOT NULL,
    location VARCHAR(13) NOT NULL,

    PRIMARY KEY(run_id)
);