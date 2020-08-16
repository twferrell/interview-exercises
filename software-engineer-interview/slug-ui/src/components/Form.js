import React from 'react';

import {useForm} from '../hooks/validator';
import {useAsyncEndpoint} from '../hooks/fetch';

const slugsAPI = "/api/slugs";

function postSlugEndpoint() {
	/* eslint-disable react-hooks/rules-of-hooks */
	return useAsyncEndpoint(data => ({
		url: slugsAPI,
		method: "POST",
		data
	}));
}

const Form = () => {
	const defaultValues = {
		url: '',
		description: '',
		slugId: ''
	};

	const customErrorAttribute = {
		className: 'has-error'
	};

	const [resp, postSlug] = postSlugEndpoint();
	const {values, setValues, useInput, isValid} = useForm(defaultValues, customErrorAttribute);

	useAsyncEndpoint({
		url: "/api/slug"
	})

	const handleSubmit = e => {
		// Prevent default form submission behavior,
		// as we will instead make an "ajax" request via Axios.
		e.preventDefault();

		// Reset our state so the form is cleared
		postSlug({
			url: values.url,
			description: values.description
		})

		setValues({
			url: '',
			description: ''
		})
	};

	return (
		<form onSubmit={handleSubmit}>
			<div className="form-body">
				<label>URL:</label>
				<input
					type="text"
					{...useInput('url', {
						isRequired: true,
						isURL: {protocols: ['http', 'https'], require_protocol: true}
					})}
				/>

				<label>DESCRIPTION:</label>
				<input
					type="text"
					{...useInput('description', 'isRequired')}
				/>
			</div>

			<button type="submit" disabled={!isValid}>
				Create Slug
			</button>

			<hr/>

			{resp && resp.complete && !resp.error && <a className="slug-url" href={resp.data.url}>Slug: {resp.data.id}</a>}

			{
				resp &&
				resp.pending &&
				<span className="loading-message">Busy making your slug!...</span>
			}

			{
				resp &&
				resp.complete &&
				resp.error &&
				<span className="error-message">Sorry, an error occurred while trying to create your slug!</span>
			}

		</form>
	);
};

export default Form;
