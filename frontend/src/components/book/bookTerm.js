import React from 'react';
import {Link} from 'react-router-dom';

const BookTerm = (props) => {
    return (
        <tr>
            <td>{props.term.name}</td>
            <td>{props.term.category}</td>
            <td>{props.term.author.name}</td>
            <td>{props.term.availableCopies}</td>
            <td className={"text-right"}>
                <a title={"Delete"} className={"btn btn-danger"}
                   onClick={() => props.onDelete(props.term.id)}>
                    Delete
                </a>
                <Link className={"btn btn-info ml-2"}
                      onClick={() => props.onEdit(props.term.id)}
                      to={`/books/edit/${props.term.id}`}>
                    Edit
                </Link>
                <a title={"Rent"} className={"btn btn-outline-info"}
                   onClick={() => props.onRent(props.term.id)}>
                    Rent
                </a>
                <a title={"Usability"} className={"btn btn-secondary"}
                   onClick={() => props.onMarkUsability(props.term.id)}>
                    mark usability/Un
                </a>
            </td>
        </tr>
    )
}

export default BookTerm;